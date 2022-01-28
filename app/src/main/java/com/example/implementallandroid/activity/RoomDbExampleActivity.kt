package com.example.implementallandroid.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.implementallandroid.R
import com.example.implementallandroid.databinding.ActivityRoomDbExampleBinding
import com.example.implementallandroid.room.EntityLoginTableModel
import com.example.implementallandroid.room.ItemsRVAdapter
import com.example.implementallandroid.room.LoginViewModel

class RoomDbExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomDbExampleBinding
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRoomDbExampleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.addButton.setOnClickListener {
            val strUsername = binding.etUserName.text.toString().trim()
            val strPassword = binding.etPassword.text.toString().trim()

            when {
                strUsername.isEmpty() -> {
                    Toast.makeText(this,"Please enter the username", Toast.LENGTH_SHORT).show()
                }
                strPassword.isEmpty() -> {
                    Toast.makeText(this,"Please enter the password", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    loginViewModel.insertData(this, strUsername, strPassword)
                    Toast.makeText(this,"Inserted Successfully", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.rvItems.layoutManager = LinearLayoutManager(this)

        loginViewModel.getAllLoginDetails(this)
        loginViewModel.liveDataLoginTableList?.observe(this, Observer<List<EntityLoginTableModel?>?> { itemList->

            val adapter = ItemsRVAdapter(
                itemList as ArrayList<EntityLoginTableModel?>,
                removeItem = {removeInfo: Any -> removeDetail(removeInfo)},
                updateItem = {updateInfo: Any -> updateDetail(updateInfo)}
            )
            binding.rvItems.adapter = adapter
            adapter.notifyDataSetChanged()

        })
    }
    private fun removeDetail(id:Any) {
        loginViewModel.removeDetailItem(this,id)
        Toast.makeText(this,"Remove Successfully", Toast.LENGTH_SHORT).show()

    }

    private fun updateDetail(item:Any) {
        val entityLoginTableModel:EntityLoginTableModel = item as EntityLoginTableModel
        showDialog(entityLoginTableModel)
    }

    private fun showDialog(entityLoginTableModel:EntityLoginTableModel) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)

        val user = dialog.findViewById<EditText>(R.id.new_user)
        val password = dialog.findViewById<EditText>(R.id.new_password)
        dialog.findViewById<Button>(R.id.update_button).setOnClickListener {
            entityLoginTableModel.username = user.text.toString()
            entityLoginTableModel.password = password.text.toString()

            loginViewModel.updateDetailItem(this,entityLoginTableModel)
            Toast.makeText(this,"Updated Successfully", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
        }
        dialog.show()
    }
}