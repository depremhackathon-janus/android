package com.janus.deprem.ui.currentstatus

import com.janus.deprem.R
import com.janus.deprem.base.BaseFragment
import com.janus.deprem.databinding.FragmentCurrentStatusBinding
import com.janus.deprem.vm.CurrentStatusVM


class CurrentStatusActivityFragment : BaseFragment<CurrentStatusVM, FragmentCurrentStatusBinding>() {
    override val getLayoutId: Int = R.layout.fragment_current_status
    override val viewModelClass = CurrentStatusVM::class.java
}


