package com.example.abacus.roomdatabase;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.example.abacus.roomdatabase.ui.NotesListFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NotesListFragment(), NotesListFragment.TAG)
                .commitNow();
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        NotesListFragment fragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(NotesListFragment.TAG);
        if (fragment != null) {
            fragment.showNoteDialog(false, null, -1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete_all) {
            NotesListFragment fragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(NotesListFragment.TAG);
            if (fragment != null) {
                fragment.deleteAllNotes();
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
