package main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import main.fragment.tab.GatherFragment;
import main.fragment.tab.ExchangeFragment;
import main.fragment.tab.MarketFragment;
import main.fragment.tab.DonateFragment;
import psj.hahaha.R;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;
    FragmentPagerAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setNavigationDrawer(savedInstanceState);
        setFragment();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 현재 탭의 레이아웃 전체를 읽어옴
        final LinearLayout linearLayout = (LinearLayout) smartTabLayout.getChildAt(0);

        // 프래그먼트 형태로 선택된 포지션의 프래그먼트를 불러온다. 만약 널이면 재실행
        Fragment fragment = ((FragmentPagerAdapter) viewPager.getAdapter()).getItem(position);
        if (fragment != null) onResume();

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView tabTextView = (TextView) linearLayout.getChildAt(i);
            // 현재 선택된 부분의 글씨색
            if (i == position) {
                tabTextView.setTextColor(getResources().getColor(R.color.navigationBarColor));
            } else {
                tabTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    // 페이지 세팅해 주는 부분
    private void setFragment() {

        // 각 페이지에 대한 프레그먼트(아이템 세팅 해 주는 부분)
        viewAdapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("모여라", GatherFragment.class)
                .add("교환", ExchangeFragment.class)
                .add("마켓", MarketFragment.class)
                .add("보물찾기", DonateFragment.class)
                .create()
        );

        // 뷰 페이저에 대한 세팅 부분
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        // 현재 임플리먼트로 온페이지체인지리스너를 가져오기 때문에, this로 처리가능
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(viewAdapter);

        smartTabLayout = (SmartTabLayout) findViewById(R.id.main_viewpagertab);
        smartTabLayout.setDistributeEvenly(true);
        smartTabLayout.setViewPager(viewPager);

        final LinearLayout linearLayout = (LinearLayout) smartTabLayout.getChildAt(0);
        TextView tab_title = (TextView) linearLayout.getChildAt(0);
        tab_title.setTextColor(getResources().getColor(R.color.navigationBarColor));

        //탭이동 설정 (교환, 마켓, 기부 글 작성 액티비티에서 작성을 완료하지 않고 메인 액티비티로 돌아올 경우
        //이전에 화면에 나와있던 탭을 보여줌)
        if(getIntent().getStringExtra("write")!=null && getIntent().getStringExtra("write").equals("ex")){
            viewPager.setCurrentItem(1);
        }else if(getIntent().getStringExtra("write")!=null && getIntent().getStringExtra("write").equals("mk")){
            viewPager.setCurrentItem(2);
        }else if(getIntent().getStringExtra("write")!=null && getIntent().getStringExtra("write").equals("dn")){
            viewPager.setCurrentItem(3);
        }

    }
}