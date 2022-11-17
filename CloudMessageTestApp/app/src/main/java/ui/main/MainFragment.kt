package ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.zup.cloudmessagetestapp.databinding.FragmentMainBinding
import br.com.zup.cloudmessagetestapp.domain.MainViewModel

class MainFragment: Fragment() {
    private lateinit var binding: FragmentMainBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initObservers()
    }

    private fun initObservers() {
        viewModel.currentToken.observe(this.viewLifecycleOwner) {
            binding.tvToken.text = it
        }
        viewModel.lastNotification.observe(this.viewLifecycleOwner) {
            binding.tvNotificationTitle.text = it.title
            binding.tvNotificationBody.text = it.body
        }
    }
}
