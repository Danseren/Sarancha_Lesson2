package ru.aston.sarancha_lesson2.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.aston.sarancha_lesson2.MyViewDto
import ru.aston.sarancha_lesson2.databinding.RecyclerAbroadCountryBinding
import ru.aston.sarancha_lesson2.databinding.RecyclerItemBinding

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(myViewDto: MyViewDto)
}

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