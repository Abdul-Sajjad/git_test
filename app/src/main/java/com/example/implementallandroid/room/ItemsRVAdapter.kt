package com.example.implementallandroid.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.implementallandroid.databinding.ItemRowBinding

class ItemsRVAdapter(private var itemsCells: ArrayList<EntityLoginTableModel?>, val removeItem : (Any) -> Unit, val updateItem : (Any) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return itemsCells.size
    }

    class ItemViewHolder(var viewBinding: ItemRowBinding) : RecyclerView.ViewHolder(viewBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemViewHolder
        itemViewHolder.viewBinding.apply {
            itemId.text = itemsCells[position]?.id.toString()
            userName.text = itemsCells[position]?.username
            password.text = itemsCells[position]?.password
            removeItem.setOnClickListener {
                removeItem(itemsCells[position] as EntityLoginTableModel)
            }
            updateItem.setOnClickListener {
                updateItem(itemsCells[position] as EntityLoginTableModel)
            }
        }
//        itemViewHolder.viewBinding.itemId.text = itemsCells[position]?.id.toString()
//        itemViewHolder.viewBinding.userName.text = itemsCells[position]?.username
//        itemViewHolder.viewBinding.password.text = itemsCells[position]?.password
//        itemViewHolder.viewBinding.removeItem.setOnClickListener {
//            removeItem(itemsCells[position] as EntityLoginTableModel)
//        }
//        itemViewHolder.viewBinding.updateItem.setOnClickListener {
//            updateItem(itemsCells[position] as EntityLoginTableModel)
//        }
    }
}