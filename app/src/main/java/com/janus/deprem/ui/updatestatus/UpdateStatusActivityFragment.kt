package com.janus.deprem.ui.updatestatus

import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import com.janus.deprem.R
import com.janus.deprem.base.BaseFragment
import com.janus.deprem.databinding.FragmentUpdateStatusBinding
import com.janus.deprem.ui.common.DetailsLookup
import com.janus.deprem.ui.common.KeyProvider
import com.janus.deprem.ui.common.Predicate
import com.janus.deprem.vm.UpdateStatusVM


class UpdateStatusActivityFragment : BaseFragment<UpdateStatusVM, FragmentUpdateStatusBinding>() {
    override val getLayoutId: Int = R.layout.fragment_update_status
    override val viewModelClass = UpdateStatusVM::class.java


    override fun onBindingCreated() {
        with(binding.rvStatuses) {
            adapter = StatusAdapter()
            (adapter as StatusAdapter).tracker = SelectionTracker.Builder(
                "status_id",
                this,
                KeyProvider(),
                DetailsLookup(this),
                StorageStrategy.createLongStorage()
            )
                .withSelectionPredicate(Predicate())
                .build().also {
                    it.addObserver(
                        object : SelectionTracker.SelectionObserver<Long>() {
                            override fun onSelectionChanged() {
                                viewModel.updateSelection(it.selection)
                            }
                        }
                    )
                }
        }
    }
}


