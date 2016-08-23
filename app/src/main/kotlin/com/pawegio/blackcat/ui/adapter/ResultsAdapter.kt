package com.pawegio.blackcat.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.pawegio.blackcat.R
import com.pawegio.blackcat.domain.RepositoryResult
import com.pawegio.blackcat.domain.SearchResult
import com.pawegio.blackcat.domain.UserResult
import com.pawegio.kandroid.inflateLayout
import kotlinx.android.synthetic.main.item_result.view.*

/**
 * @author pawegio
 */
class ResultsAdapter(val onClick: (UserResult) -> Unit) : RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    private val data = mutableListOf<SearchResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.context.inflateLayout(R.layout.item_result, parent, false), onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItem(data[position])
    }

    override fun getItemCount() = data.size

    fun updateData(results: List<SearchResult>) {
        data.clear()
        data.addAll(results)
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View, val onClick: (UserResult) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindItem(item: SearchResult) {
            with(itemView) {
                when(item) {
                    is UserResult -> {
                        titleView.text = item.login
                        typeView.text = "User"
                        setOnClickListener { onClick(item) }
                    }
                    is RepositoryResult -> {
                        titleView.text = item.name
                        typeView.text = "Repository"
                        setOnClickListener(null)
                    }
                }
            }
        }
    }
}