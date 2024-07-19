package com.example.postapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClientPostAdapter(var clientinfo:List<Post>) :RecyclerView.Adapter<ClientInformationHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientInformationHolder {
        val postinfo=LayoutInflater.from(parent.context).inflate(R.layout.clients_postinformation_item,parent,false)
        return ClientInformationHolder(postinfo)
    }

    override fun getItemCount(): Int {
     return clientinfo.size
    }

    override fun onBindViewHolder(holder: ClientInformationHolder, position: Int) {
     val updatedClientInformation=clientinfo[position]
        holder.clientBody.text=updatedClientInformation.body
        holder.clientTittle.text=updatedClientInformation.title
        holder.clientUserId.text="UserId:${updatedClientInformation.userId}"
        holder.clientId.text="Id:${updatedClientInformation.id}"


    }

}


class ClientInformationHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var clientBody=itemView.findViewById<TextView>(R.id.wyBody)
    var clientTittle=itemView.findViewById<TextView>(R.id.wyTittle)
    var clientUserId=itemView.findViewById<TextView>(R.id.wyUserId)
    var clientId=itemView.findViewById<TextView>(R.id.wyId)


}