package com.cibertec.examenfinaldam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibertec.examenfinaldam.VideoREST.Video

class VideoAdapter(private val mlist:List<Video>) : RecyclerView.Adapter<VideoAdapter.ViewHolder
        >(){

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView){
        val txtTitulo : TextView = ItemView.findViewById(R.id.txtTitulo)
        val imageViewVideo: ImageView = ItemView.findViewById(R.id.imageViewVideo)
        val txtDescripcion : TextView = ItemView.findViewById(R.id.txtDescripcion)
        val txtAutor : TextView = ItemView.findViewById(R.id.txtAutor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_video, parent, false)
        return VideoAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    override fun onBindViewHolder(holder: VideoAdapter.ViewHolder, position: Int) {
        val Video = mlist[position]
        holder.txtTitulo.text = Video.title
        val imageViewVideo = Video.thumb
        Glide.with(holder.itemView.context)
            .load(imageViewVideo)
            .into(holder.imageViewVideo)
        holder.txtDescripcion.text = Video.description
        holder.txtAutor.text = Video.subtitle

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }


}