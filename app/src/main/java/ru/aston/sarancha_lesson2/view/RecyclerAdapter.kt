package ru.aston.sarancha_lesson2.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.aston.sarancha_lesson2.MyViewDto
import ru.aston.sarancha_lesson2.Utils.MOTHERLAND
import ru.aston.sarancha_lesson2.databinding.RecyclerAbroadCountryBinding
import ru.aston.sarancha_lesson2.databinding.RecyclerItemBinding


class RecyclerAdapter(private val listData: List<MyViewDto>) :
    RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {

    var clickAction: ((MyViewDto) -> Unit)? = null
    var itemPos: Int = 1

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        return when (viewType) {
            MOTHERLAND -> {
                val binding =
                    RecyclerAbroadCountryBinding.inflate(LayoutInflater.from(parent.context))
                AbroadCountryViewHolder(binding)
            }
            else -> {
                val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
                MyViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position])
        when (holder) {
            is MyViewHolder -> {
                with(holder.binding) {
                    custom.apply {
                        setHeaderText(listData[position].headerText)
                        setBodyText(listData[position].bodyText)
                        setImageSrc(listData[position].imageSrc)
                        setOnClickListener {
                            itemPos = position
                            clickAction?.invoke(listData[position])
                        }
                    }

                }
            }
            is AbroadCountryViewHolder -> {
                with(holder.binding) {
                    custom.apply {

                        setHeaderText(listData[position].headerText)
                        setBodyText(listData[position].bodyText)
                        setImageSrc(listData[position].imageSrc)
                        setOnClickListener {
                            itemPos = position
                            clickAction?.invoke(listData[position])
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = listData.size

    class MyViewHolder(
        val binding: RecyclerItemBinding
    ) : BaseViewHolder(binding.root) {
        override fun bind(myViewDto: MyViewDto) {}

    }

    class AbroadCountryViewHolder(
        val binding: RecyclerAbroadCountryBinding
    ) : BaseViewHolder(binding.root) {
        override fun bind(myViewDto: MyViewDto) {}
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(myViewDto: MyViewDto)
    }
}


