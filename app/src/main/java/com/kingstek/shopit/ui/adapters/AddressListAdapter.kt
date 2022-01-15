package com.kingstek.shopit.ui.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kingstek.shopit.R
import com.kingstek.shopit.models.Address
import com.kingstek.shopit.ui.activities.AddEditAddressActivity
import com.kingstek.shopit.utils.Constants
import kotlinx.android.synthetic.main.item_address_layout.view.*

open class AddressListAdapter(private val context: Context, private var list: ArrayList<Address>, private val selectAddress: Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_address_layout, parent, false))
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder) {
            holder.itemView.tv_address_full_name.text = model.name
            holder.itemView.tv_address_type.text = model.type
            holder.itemView.tv_address_details.text = "${model.address}, ${model.zipCode}"
            holder.itemView.tv_address_mobile_number.text = model.mobileNumber

            if (selectAddress) {
                holder.itemView.setOnClickListener {
                    Toast.makeText(
                        context,
                        "Selected address : ${model.address}, ${model.zipCode}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            // END
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun notifyEditItem(activity: Activity, position: Int) {
        val intent = Intent(context, AddEditAddressActivity::class.java)
        intent.putExtra(Constants.EXTRA_ADDRESS_DETAILS, list[position])

        activity.startActivityForResult(intent, Constants.ADD_ADDRESS_REQUEST_CODE)

        notifyItemChanged(position) // Notify any registered observers that the item at position has changed.
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}