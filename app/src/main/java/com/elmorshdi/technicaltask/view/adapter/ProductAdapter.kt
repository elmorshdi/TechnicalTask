package com.elmorshdi.technicaltask.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elmorshdi.technicaltask.data.model.Product
import com.elmorshdi.technicaltask.databinding.ItemBinding

class ProductAdapter(
        private val interaction: Interaction? = null
    ): ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

        class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }

        class ProductViewHolder constructor(
            private val binding: ItemBinding,
            private val interaction: Interaction?
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind(product: Product ) = with(itemView) {
                if (product.stock!! >50){
                    binding.container.layoutDirection= View.LAYOUT_DIRECTION_LTR
                }
                else{
                    binding.container.layoutDirection= View.LAYOUT_DIRECTION_RTL

                }
                binding.itemDescription.text=product.description
                binding.itemName.text=product.title
                val textPrice = StringBuilder().append(product.price.toString()).append(" ").append("LE").toString()

                binding.itemPrice.text=textPrice
                //Notify the listener on item click
                itemView.setOnClickListener {
                    interaction?.onItemSelected(product)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding=ItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
            return ProductViewHolder(
                binding , interaction
            )
        }


        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            val product = getItem(position)
            holder.bind(product)
        }









        interface Interaction {
            fun onItemSelected(product: Product)

        }


    }


