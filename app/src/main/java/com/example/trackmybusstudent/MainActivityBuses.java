package com.example.trackmybusstudent;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MainActivityBuses extends AppCompatActivity {

NavigationView nav;
//ActionBarDrawerToggle toggle;
DrawerLayout drawerLayout;
Toolbar toolbar;
BottomNavigationView btmNav;
NavController navController;
View header;
FirebaseAuth auth;
FirebaseUser user;
AppBarConfiguration appBarConfiguration;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_buses);


toolbar = findViewById(R.id.toolbar);
setSupportActionBar(toolbar);
toolbar.setTitleTextColor(getResources().getColor(R.color.white));
getSupportActionBar().setTitle("Track Buses Location");
toolbar.setBackgroundColor(getResources().getColor(R.color.black));

nav = findViewById(R.id.drawernav);
drawerLayout = findViewById(R.id.drawerlayout);
btmNav = (BottomNavigationView) findViewById(R.id.btmnav);
navController = Navigation.findNavController(this,R.id.fragment_container);

appBarConfiguration = new AppBarConfiguration.Builder(new int[]{R.id.myProfileFragment, R.id.mapFragment,R.id.recViewFragment,
        R.id.busFragment, R.id.infoFragment}).
        setOpenableLayout(drawerLayout).build();

NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
NavigationUI.setupWithNavController(nav,navController);
NavigationUI.setupWithNavController(btmNav,navController);


/*toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.open,R.string.close);
drawerLayout.addDrawerListener(toggle);
toggle.syncState();*/
auth = FirebaseAuth.getInstance();
user = auth.getCurrentUser();
//toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

      //  nav.getMenu().findItem(R.id.dnavall).setChecked(true);
//nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Fragment selectdnav = null;
//        switch (item.getItemId()){
//            case R.id.dnavprofile:
//                selectdnav = new MyProfileFragment();
//                nav.getMenu().findItem(R.id.dnavprofile).setChecked(true);
//                drawerLayout.closeDrawer(GravityCompat.START);
//                getSupportActionBar().setTitle("My Profile");
//                break;
//            case R.id.dnavmap:
//                selectdnav = new MapFragment();
//                drawerLayout.closeDrawer(GravityCompat.START);
//                nav.getMenu().findItem(R.id.dnavmap).setChecked(true);
//                getSupportActionBar().setTitle("Google Map");
//                break;
//            case R.id.dnavall:
//                selectdnav =  new RecViewFragment();
//                drawerLayout.closeDrawer(GravityCompat.START);
//                nav.getMenu().findItem(R.id.dnavall).setChecked(true);
//                getSupportActionBar().setTitle("Track Buses Location");
//            case R.id.dnavmybus:
//                selectdnav = new BusFragment();
//                drawerLayout.closeDrawer(GravityCompat.START);
//                nav.getMenu().findItem(R.id.dnavmybus).setChecked(true);
//                getSupportActionBar().setTitle("My Bus");
//                break;
//            case R.id.dnavinfo:
//                selectdnav = new InfoFragment();
//                drawerLayout.closeDrawer(GravityCompat.START);
//                nav.getMenu().findItem(R.id.dnavinfo).setChecked(true);
//                getSupportActionBar().setTitle("Information");
//                break;
//        }
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectdnav).commit();
//        return true;
//    }
//});
//nav.getMenu().findItem(R.id.dnavall).setChecked(true);

//
//        btmNav.setOnNavigationItemSelectedListener((navListner));
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RecViewFragment()).commit();
//     //   btmNav.setOnNavigationItemSelectedListener(this);
//        btmNav.getMenu().findItem(R.id.navhome).setChecked(true);

    header = nav.getHeaderView(0);
    if(user != null){
//        TextView headername = (TextView) header.findViewById(R.id.drawername);
//        headername.setText(user.getDisplayName());
        TextView headeremail = (TextView) header.findViewById(R.id.draweremail);
        headeremail.setText(user.getEmail());
//        ImageView headerimage = (ImageView) header.findViewById(R.id.drawerimage);
//        Picasso.get().load(user.getPhotoUrl()).placeholder(getDrawable(R.drawable.ic_action_user));
    }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration );
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Fragment selectedFragment = null;
//            switch (item.getItemId()){
//                case R.id.navprofile:
//                    selectedFragment = new MyProfileFragment();
//                    getSupportActionBar().setTitle("My Profile");
//                    //btmNav.getMenu().findItem(R.id.navprofile).setChecked(true);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyProfileFragment()).commit();
//                    break;
//                case R.id.navmap:
//                    getSupportActionBar().setTitle("Google Map");
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapFragment()).commit();
////                    Intent intent = new Intent(MainActivityBuses.this, MapActivity.class);
////                    startActivity(intent);
////                    finish();
//                   /* if(selectedFragment == new MyProfileFragment()){
//                        btmNav.getMenu().findItem(R.id.navprofile).setChecked(true);}
//                    else if(selectedFragment == new RecViewFragment())
//                        btmNav.getMenu().findItem(R.id.navhome);
//                    else if(selectedFragment == new BusFragment()){
//                        btmNav.getMenu().findItem(R.id.navmybus).setChecked(true);}
//                    else if (selectedFragment == new InfoFragment()){
//                        btmNav.getMenu().findItem(R.id.navinfo);}*/
//
//                    break;
//                case R.id.navhome:
//                    //btmNav.getMenu().findItem(R.id.navhome).setChecked(true);
//                    selectedFragment = new RecViewFragment();
//                    getSupportActionBar().setTitle("Track Buses Location");
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RecViewFragment()).commit();
//                    break;
//                case R.id.navmybus:
//                    //btmNav.getMenu().findItem(R.id.navmybus).setChecked(true);
//                    selectedFragment = new BusFragment();
//                    getSupportActionBar().setTitle("My Bus");
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new BusFragment()).commit();
//                    break;
//                case R.id.navinfo:
//                    //btmNav.getMenu().findItem(R.id.navinfo).setChecked(true);
//                    selectedFragment = new InfoFragment();
//                    getSupportActionBar().setTitle("Information");
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new InfoFragment()).commit();
//                    break;
//            }
//           // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
//            return true;
//        }
//    };


}
