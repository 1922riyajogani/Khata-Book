package com.example.khata_book.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.khata_book.Model.TransModel
import kotlin.math.round

class TransListAdapter(update:(TransModel) -> Unit,delete: (Int) -> Unit) :
         RecyclerView.Adapter<TransListAdapter.TransListHolder>() {

       var update=update
       var delete=delete
       var transList = ArrayList<TransModel>()
       lateinit var context: Context

       class  TransListHolder(itemView: TransItemBinding):ViewHolder(itemView.root){
              var binding =itemView
       }

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransListHolder {
              context = parent.context
              var binding=TransItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
              return TransListHolder(binding)
       }

       override fun getItemCount(): Int {
              return transList.size
       }

       override fun onBindViewHolder(
              holder: TransListHolder,
              @SuppressLint("RecyclerView")position: Int
       ) {
           holder.binding.apply{
                 transList.get(position).apply {
                        txtCategory.text=category
                        txtNote.text=note
                        txtAmount.text=amount.toString()

                        if (isExpense == 0) {
                               txtAmount.setTextColor(Color.GREEN)
                               round.setImageResource(R.drawable.arrow_upward)
                               roundback.setImageResource(R.drawable.green_round_shape)
                        }else{
                               roundback.setImageResource(R.drawable.green_round_shape)
                               round.setImageResource(R.drawable.arrow_upward)
                               txtAmount.setTextColor(Color.RED)
                        }
                 }
           }
           holder.itemView.setOnClickListener(object : OnLongClickListener {
                  override fun onLongClick(p0: View?): Boolean {

                         var popupMenu = PopupMenu(context,holder.itemView)
                         popupMenu.menuInflater.inflate(R_menu.trans_menu,popupMenu.menu)

                         popupMenu.setOnMenuItemClickListener(object :PopupMenu.OnMenuItemClickListener{
                                override fun onMenuItemClick(p0: MenuItem?): Boolean {

                                       if (p0?.itemId==R.id.update){
                                              update.invoke(transList.get(position))
                                       }

                                       if (p0?.itemId==R.id.delete){
                                              delete.invoke(transList.get(position).id)
                                       }
                                       return true
                                }
                         })

                         popupMenu.show()

                         return false
                  }
           })

       }

       fun setTrans(transList: ArrayList<TransModel>) {
              this.transList = transList
       }

       fun updateData(transaction:ArrayList<TransModel>) {
              this.transList = transaction
              notifyDataSetChanged()
       }

}