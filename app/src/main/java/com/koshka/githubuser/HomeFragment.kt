package com.koshka.githubuser

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.koshka.githubuser.adapter.Adapter
import com.koshka.githubuser.network.ApiClient
import com.koshka.githubuser.network.ApiInterface
import com.koshka.githubuser.viewModel.UserViewModel

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var searchView: SearchView
    private lateinit var mApiInterface: ApiInterface
    private lateinit var mainViewModel: UserViewModel
    private lateinit var adapter: Adapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = view.findViewById(R.id.search_view) as SearchView
        mApiInterface = ApiClient.getClient().create(ApiInterface::class.java)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        mainViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(UserViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = getString(R.string.search_hint)
    }

    private fun getUser() {
        mainViewModel.getUser().observe(viewLifecycleOwner, { userItems ->
            adapter = Adapter(userItems)
            adapter.notifyDataSetChanged()
            recyclerView.adapter = adapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search_view).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        }
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextSubmit(text: String?): Boolean {
        mainViewModel.setUser(text!!)
        getUser()
        return false
    }

}