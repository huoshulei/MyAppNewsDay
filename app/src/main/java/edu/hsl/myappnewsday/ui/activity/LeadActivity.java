package edu.hsl.myappnewsday.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.common.util.PreserveUtil;
import edu.hsl.myappnewsday.ui.base.BaseActivity;
import edu.hsl.myappnewsday.ui.frgment.CartoonFragment;
import edu.hsl.myappnewsday.ui.frgment.LeadFragment;


public class LeadActivity extends BaseActivity {
    LeadFragment frag_lead;
    boolean isFirstRun = true;
    CartoonFragment cartoon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        isFirstRun = PreserveUtil.getBoolean(getApplicationContext(), "isFirstRun");
//        if (isFirstRun) {
        setDefaultFragment();
//        } else {
//            FragmentManager     manager     = getFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            cartoon = new CartoonFragment();
//            transaction.replace(R.id.fl_lead, cartoon);
//            transaction.commit();
//        }
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
