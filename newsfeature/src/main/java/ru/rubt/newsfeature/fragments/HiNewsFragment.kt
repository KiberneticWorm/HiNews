package ru.rubt.newsfeature.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.rubt.data.remote.states.EmptyHiNewsState
import ru.rubt.data.remote.states.NoUpdatedHiNewsState
import ru.rubt.data.remote.states.UpdatedHiNewsState
import ru.rubt.newsfeature.R
import ru.rubt.newsfeature.adapters.HiNewsAdapter
import ru.rubt.newsfeature.databinding.FragmentHiNewsBinding
import ru.rubt.newsfeature.di.HiNewsComponent
import ru.rubt.newsfeature.di.HiNewsComponentProvider
import ru.rubt.newsfeature.di.HiNewsViewModelFactory
import ru.rubt.newsfeature.fragments.interfaces.StatusErrorListener
import ru.rubt.newsfeature.fragments.status.EmptyHiNewsStatus
import ru.rubt.newsfeature.fragments.status.NetworkErrorStatus
import ru.rubt.newsfeature.viewmodels.HiNewsViewModel
import javax.inject.Inject

class HiNewsFragment: Fragment() {

    @Inject
    lateinit var hiNewsViewModelFactory: HiNewsViewModelFactory

    private val hiNewsViewModel by viewModels<HiNewsViewModel> { hiNewsViewModelFactory }

    private lateinit var hiNewsComponent: HiNewsComponent

    private lateinit var statusErrorListener: StatusErrorListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        hiNewsComponent = (requireActivity().application as HiNewsComponentProvider).provideHiNewsComponent()
        hiNewsComponent.inject(this)

        statusErrorListener = context as StatusErrorListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentHiNewsBinding: FragmentHiNewsBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_hi_news, container, false)

        if (savedInstanceState == null) {

            loadHiNews(fragmentHiNewsBinding)

        } else {

            toggleLoading(fragmentHiNewsBinding)
            fragmentHiNewsBinding.rvHiNews.adapter = HiNewsAdapter(hiNewsViewModel.getCachedHiNews())

        }

        return fragmentHiNewsBinding.root
    }

    private fun toggleLoading(fragmentHiNewsBinding: FragmentHiNewsBinding) {
        fragmentHiNewsBinding.progressLoad.visibility = View.GONE
        fragmentHiNewsBinding.rvHiNews.visibility = View.VISIBLE
        fragmentHiNewsBinding.rvHiNews.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun loadHiNews(fragmentHiNewsBinding: FragmentHiNewsBinding) {

        val theme = requireArguments().getString(EXTRA_THEME) ?: failedTheme()

        hiNewsViewModel.getUpdatedHiNews(theme).observe(viewLifecycleOwner, Observer {

            toggleLoading(fragmentHiNewsBinding)

            when (it) {
                is NoUpdatedHiNewsState -> {
                    fragmentHiNewsBinding.rvHiNews.adapter = HiNewsAdapter(it.noUpdatedHiNews)
                    statusErrorListener.showStatus(NetworkErrorStatus(R.string.update_failed))
                }
                is EmptyHiNewsState -> {
                    statusErrorListener.showStatus(EmptyHiNewsStatus(R.string.update_failed))
                }
                is UpdatedHiNewsState -> {
                    fragmentHiNewsBinding.rvHiNews.adapter = HiNewsAdapter(it.updatedHiNews)
                }
            }
        })
    }

    private fun failedTheme(): Nothing = throw Exception("Theme is null!")

    companion object {

        private const val EXTRA_THEME = "theme"

        fun newInstance(theme: String): HiNewsFragment {

            val fragment = HiNewsFragment()

            fragment.arguments = bundleOf(EXTRA_THEME to theme)

            return fragment
        }
    }

}