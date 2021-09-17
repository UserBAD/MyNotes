package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mynotes.domain.Notes;
import com.example.mynotes.ui.Router;
import com.example.mynotes.ui.details.NotesDetailsActivity;
import com.example.mynotes.ui.list.NotesListFragment;
import com.example.mynotes.ui.list.NotesListPresenter;
import com.example.mynotes.ui.list.RouterHolder;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNotesClicked {

    private NotesListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.append) {
                    Toast.makeText(MainActivity.this, "Добавить", Toast.LENGTH_SHORT).show();
//                    presenter.addNote(UUID.randomUUID().toString(), "https://cdn.pixabay.com/photo/2021/06/22/14/55/girl-6356393_960_720.jpg");
                    return true;
                }
                if (item.getItemId() == R.id.to_share) {
                    Toast.makeText(MainActivity.this, "Поделиться", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });


        Toolbar toolbarDrawer = findViewById(R.id.drawer);
        setSupportActionBar(toolbarDrawer);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.settings) {
                    Toast.makeText(MainActivity.this, "Настройки", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (item.getItemId() == R.id.about_the_program) {
                    Toast.makeText(MainActivity.this, "О программе", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onNotesOnClicked(Notes notes) {

        if (!getResources().getBoolean(R.bool.isLandscape)) {

            Intent intent = new Intent(this, NotesDetailsActivity.class);
            intent.putExtra(NotesDetailsActivity.ARG_NOTE, notes);
            startActivity(intent);
        }
    }


}