package aueb.msc.component

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import aueb.msc.R
import aueb.msc.VehicleMonitorActivity
import aueb.msc.model.Profile

class ProfileRecycleViewAdapter(private val context: Context, private val data: List<Profile>) : RecyclerView.Adapter<ProfileRecycleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.profile_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.textViewItem.text = item.name
        holder.selectProfileButton.setOnClickListener() {
            val intent = Intent(context, VehicleMonitorActivity::class.java)
            intent.putExtra("profile", item.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewItem: TextView = itemView.findViewById(R.id.profile_name)
        val selectProfileButton: Button = itemView.findViewById(R.id.select_profile_button)
    }
}