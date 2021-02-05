package com.koshka.githubuser

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.koshka.githubuser.adapter.Adapter
import com.koshka.githubuser.network.ApiClient
import com.koshka.githubuser.network.ApiInterface
import com.koshka.githubuser.viewModel.UserViewModel

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    lateinit var searchView: SearchView
    lateinit var mApiInterface: ApiInterface
    lateinit var mainViewModel: UserViewModel
    private lateinit var adapter: com.koshka.githubuser.adapter.Adapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        mainViewModel.setUser("hanif")
        mainViewModel.getUser().observe(viewLifecycleOwner, { userItems ->
            adapter = Adapter(userItems)
            adapter.notifyDataSetChanged()
            recyclerView.adapter = adapter
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = getString(R.string.search_hint)

//        val user: Call<ResponseUser> = mApiInterface.getUser()
//        user.enqueue(object : retrofit2.Callback<ResponseUser> {
//            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
//                val user: ArrayList<ItemsItem> = response.body()?.items as ArrayList<ItemsItem>
//
//                adapter = com.koshka.githubuser.adapter.Adapter(user)
//                adapter.notifyDataSetChanged()
//                recyclerView.adapter = adapter
//            }
//
//            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
//                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()
//            }
//
//        })
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

    override fun onQueryTextSubmit(p0: String?): Boolean {
        Toast.makeText(activity, p0, Toast.LENGTH_SHORT).show()

        return false
    }

}