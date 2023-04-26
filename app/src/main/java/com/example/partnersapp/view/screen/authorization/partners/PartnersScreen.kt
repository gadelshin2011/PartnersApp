package com.example.partnersapp.view.screen.authorization.partners

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.partnersapp.R
import com.example.partnersapp.databinding.FragmentPartnersScreenBinding
import com.example.partnersapp.presenter.adapter.RcAdapterPartners
import com.example.partnersapp.presenter.network.WebRepository
import com.example.partnersapp.view.screen.authorization.AuthViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PartnersScreen : Fragment() {
    lateinit var binding: FragmentPartnersScreenBinding
    // TODO У тебя используется binding для доступа к view, отдельно хранить ссылку на RecyclerView нет необходимости
    private lateinit var recyclerView: RecyclerView
    // TODO в фрагменте не должно быть репозиториев (в данном случае у тебя вообще в нем содержится клиент сетевых запросов)
    var webRepo = WebRepository()
    private val adapterRc = RcAdapterPartners()
//   TODO Лучше придерживаться подхода 1 экран и 1 viewModel для него, которая будет содержать логику для работы этого экрана
//     Получается для экрана с партнерами нужна своя вьюмодель
//     Делегат by activityViewModels() позволяет создать вьюмодель с жизненным циклом, привязанным к активити.
//     Обычно такое ипользуют для передачи данных между различныцми фрагментами, через вьюмодель, но лучше таким не злоупотреблять
//     Если ты хочешь передать токен от авторизации, то храни его в отдельной сущности, которая будет жить дольше (ну как вариант, тот же самый синглтон, локальная бд, sharedPreferences, datastore preferences )

    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPartnersScreenBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    //TODO нейминг методов лучше делать "говорящим".  init в нем initialization - ничего не говорят для читающего код
    private fun init() {
        initialization()
        showData()
        setListener()
    }

    private fun setListener() {
        binding.imageButtonBack.setOnClickListener {
            findNavController().navigate(R.id.action_partnersScreen_to_authorization)
        }
    }

    private fun showData() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.partners.collect {
//                    adapterRc.setList(it)
//                }
//            }
//        }

        //TODO когда фрагмент не будет виден пользователю, то подписка и обновление UI не нужны + могут быть проблемы когда view уничтожится,
        // поэтому нужно использовать repeatOnLifecycle. Он будет вызывать и отменять выполнение блока внутри него при выходе из переданного в него стейта
        viewModel.partners.onEach {
            adapterRc.setList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.requestPartners()
    }

    private fun initialization() {
        recyclerView = binding.rcViewPartners
        recyclerView.adapter = adapterRc
    }


}