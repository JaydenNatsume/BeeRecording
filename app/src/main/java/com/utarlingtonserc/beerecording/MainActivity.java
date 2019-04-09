package com.utarlingtonserc.beerecording;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.utarlingtonserc.beerecording.Fragments.FragmentCapital;
import com.utarlingtonserc.beerecording.Fragments.FragmentHome;
import com.utarlingtonserc.beerecording.Fragments.FragmentInformation;
import com.utarlingtonserc.beerecording.Fragments.FragmentNotes;
import com.utarlingtonserc.beerecording.Fragments.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new FragmentHome());
    }

    // Replace the Current Fragment
    private boolean loadFragment(Fragment fragment){
        if(fragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Check which menu item is checked
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new FragmentHome();
                break;
            case R.id.navigation_notes:
                fragment = new FragmentNotes();
                break;
            case R.id.navigation_transaction:
                fragment = new FragmentTransaction();
                break;
            case R.id.navigation_capital:
                fragment = new FragmentCapital();
                break;
            case R.id.navigation_information:
                fragment = new FragmentInformation();
                break;
        }

        // Call the method that replaces the fragment
        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
