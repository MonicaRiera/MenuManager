package com.example.menumanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, ActionMode.Callback, View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.activity_main__tv__title);
        registerForContextMenu(textView);

        final View root = findViewById(R.id.activity_main__cl__root);
        root.setOnLongClickListener(this);
        root.setOnClickListener(this);

        final Toolbar toolbar = findViewById(R.id.activity_main__tb__main);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView helloWorld = findViewById(R.id.activity_main__tv__title);

        if (item.getItemId() == R.id.menu_main__item__copy) {
            Toast.makeText(this, "Copy", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu_main__item__cut) {
            helloWorld.setTextColor(Color.WHITE);
            Toast.makeText(this, "Cut", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu_main__item__paste) {
            helloWorld.setTextColor(Color.BLACK);
            Toast.makeText(this, "Paste", Toast.LENGTH_SHORT).show();
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        TextView helloWorld = findViewById(R.id.activity_main__tv__title);

        if (item.getItemId() == R.id.floating_menu__item__add) {
            helloWorld.setTextColor(Color.BLUE);
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.floating_menu__item__like) {
            helloWorld.setTextColor(Color.GREEN);
            Toast.makeText(this, "Like :)", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.floating_menu__item__dislike) {
            helloWorld.setTextColor(Color.RED);
            Toast.makeText(this, "Dislike :(", Toast.LENGTH_SHORT).show();
        } else {
            return super.onContextItemSelected(item);
        }

        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.activity_main__cl__root) {
            startSupportActionMode(this);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {
        TextView helloWorld = findViewById(R.id.activity_main__tv__title);

        if (item.getItemId() == R.id.floating_menu__item__add) {
            PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.floating_menu__item__add));
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_popup, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(MainActivity.this, "From pop-up!", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            popupMenu.show();
        } else if (item.getItemId() == R.id.floating_menu__item__like) {
            helloWorld.setTextColor(Color.GREEN);
            Toast.makeText(this, "Like :)", Toast.LENGTH_SHORT).show();
            mode.finish();
        } else if (item.getItemId() == R.id.floating_menu__item__dislike) {
            helloWorld.setTextColor(Color.RED);
            Toast.makeText(this, "Dislike :(", Toast.LENGTH_SHORT).show();
            mode.finish();
        } else {
            return false;
        }

        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
