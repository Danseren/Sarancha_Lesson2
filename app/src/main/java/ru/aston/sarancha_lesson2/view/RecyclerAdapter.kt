package ru.aston.sarancha_lesson2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.aston.sarancha_lesson2.Vacancy
import ru.aston.sarancha_lesson2.databinding.RecyclerItemBinding


class RecyclerAdapter(private val listData: List<Vacancy>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.binding) {
            custom.setHeaderText(listData[position].headerText)
            custom.setBodyText(listData[position].bodyText)
            custom.setImageSrc(listData[position].imageSrc)
        }
    }

    override fun getItemCount(): Int = listData.size

    class MyViewHolder(
        val binding: RecyclerItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

    }
}


