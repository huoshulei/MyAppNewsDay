package edu.hsl.myappnewsday.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

import edu.hsl.myappnewsday.R;

/**
 * Created by Administrator on 2016/6/14.
 * 新闻详情页面右侧菜单
 */
public abstract class MenuDialog {
    Context         mContext;
    FragmentManager mFragmentManager;
    private ContextMenuDialogFragment mDialogFragment;

    public MenuDialog(Context context, FragmentManager fragmentManager) {
        mContext = context;
        mFragmentManager = fragmentManager;
        initMenu();
    }

    public ContextMenuDialogFragment getDialogFragment() {
        return mDialogFragment;
    }

    public void initMenu() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) mContext.getResources().getDimension(R.dimen
                .tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mDialogFragment.setItemClickListener(getItemClickListener());
    }

    @NonNull
    private OnMenuItemClickListener getItemClickListener() {
        return new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(View clickedView, int position) {
                MenuItemClick(clickedView, position);
            }
        };
    }

    public abstract void MenuItemClick(View clickedView, int position);


    public List<MenuObject> getMenuObjects() {
        List<MenuObject> mMenuObjects = new ArrayList<>();
        MenuObject       share        = new MenuObject("分享");
        share.setResource(R.drawable.image6);
        share.setBgColor(0xff455a64);

        MenuObject favorite = new MenuObject("收藏");
        favorite.setResource(R.drawable.image5);
        favorite.setBgColor(0xff009688);

        MenuObject comment = new MenuObject("跟帖");
        comment.setResource(R.drawable.image4);
        comment.setBgColor(0xff795548);

        MenuObject praise = new MenuObject("赞");
        praise.setResource(R.drawable.image3);
        praise.setBgColor(0xffd84315);

        MenuObject share1 = new MenuObject("分享1");
        share1.setResource(R.drawable.image2);
        share1.setBgColor(0xff617023);

        MenuObject share2 = new MenuObject("分享2");
        share2.setResource(R.drawable.image1);
        share2.setBgColor(0xff1c5751);
        mMenuObjects.add(share);
        mMenuObjects.add(favorite);
        mMenuObjects.add(comment);
        mMenuObjects.add(praise);
        mMenuObjects.add(share1);
        mMenuObjects.add(share2);
        return mMenuObjects;
    }
}
