package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.myapplication.Classes.Dishes;
import com.example.myapplication.Classes.LoginHandler;
import com.example.myapplication.Classes.Restaurants;
import com.example.myapplication.Database.database;
import com.example.myapplication.databinding.ActivityUserBinding;
import com.example.myapplication.fileParsers.test;
//import com.example.myapplication.fileParsers.usersRead;

public class UserActivity extends AppCompatActivity {

    private ActivityUserBinding binding;

    @Override
    protected void onResume() {
        super.onResume();
        replaceFragment(new MenuFragment());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
//        usersRead.parser(getApplicationContext());
//        test t= new test(this);
//        t.save("aaaaa");
//        t.save("bbbb");
//        t.save("zzzz");
//       t.load();
        replaceFragment(new MenuFragment());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.ResMenu) {
                replaceFragment(new MenuFragment());
                return true;
            }else if (itemId == R.id.ResCart) {
                replaceFragment(new CartFragment());
                return true;
            }   else if (itemId == R.id.ResSearch) {
                replaceFragment(new SearchFragment());
                return true;
            } else if (itemId == R.id.ResProfile) {
                if(LoginHandler.isLoggedIn() ){

                    replaceFragment(new ProfileFragment());
                    return true;
                }else{
                    Intent intent = new Intent(UserActivity.this, login_page.class);
                    startActivity(intent);
                    return true;
                }

            } else {
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
    private void hideMenuItem(int itemId) {
        Menu menu = binding.bottomNavigationView.getMenu();
        MenuItem item = menu.findItem(itemId);
        if (item != null) {
            item.setVisible(false);
        }
    }
    public void setup(){
        database.addDish(new Dishes("Foul Sandwich","balady Bread with foul medames Sandwich", 5, Dishes.cuisines.RUSSIAN, Dishes.categories.BREAKFAST,"arabiata" ));
        database.addDish(new Dishes("Foul Box","foul medames Box", 400, Dishes.cuisines.ITALIAN, Dishes.categories.LUNCH, "arabiata"));
        database.addDish(new Dishes("Koshary Box","Large koshary Box", 70, Dishes.cuisines.MEXICAN, Dishes.categories.DINNER, "arabiata"));
        database.addDish(new Dishes("test","balady Bread with foul medames Sandwich", 5, Dishes.cuisines.RUSSIAN, Dishes.categories.BREAKFAST,"restone" ));



        database.restaurants.add(new Restaurants("arabiata","El Rehab Food court",12345,R.drawable.arabiata));
        database.restaurants.add(new Restaurants("restone","El Rehab Food court",12345,R.drawable.arabiata));

    }

}