package edu.hsl.myappnewsday.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.common.util.PreserveUtil;
import edu.hsl.myappnewsday.ui.base.BaseActivity;
import edu.hsl.myappnewsday.ui.frgment.CartoonFragment;
import edu.hsl.myappnewsday.ui.frgment.LeadFragment;

/**
 * 开机动画的一个类
 */
public class LeadActivity extends BaseActivity {
    LeadFragment frag_lead;
    boolean isFirstRun = true;//判断是否首次运行
    CartoonFragment cartoon;


    @Override
    public void initView() {
        setContentView(R.layout.activity_lead);
        //读取记录值
        isFirstRun = PreserveUtil.getBoolean(getApplicationContext(), "isFirstRun");
        setDefaultFragment();
        super.initView();
    }

    private void setDefaultFragment() {
        FragmentManager     manager     = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (isFirstRun) {
            frag_lead = new LeadFragment();
            transaction.replace(R.id.fl_lead, frag_lead);
        } else {
            cartoon = new CartoonFragment();
            transaction.replace(R.id.fl_lead, cartoon);
        }
        transaction.commit();
    }
}
