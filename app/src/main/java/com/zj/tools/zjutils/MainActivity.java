package com.zj.tools.zjutils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static final String ITEM_1 = "1. 访问@hide注释的函数";
    public static final String ITEM_2 = "2. Log打印工具";
    public static final String ITEM_3 = "3. Toast工具";
    public static final String ITEM_4 = "4. 通知工具";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);

        final ArrayList<String> data = new ArrayList<>();
        data.add(ITEM_1);
        data.add(ITEM_2);
        data.add(ITEM_3);
        data.add(ITEM_4);
        final MainAdapter adapter = new MainAdapter(data);
        adapter.setListener(new OnAdapterListener() {
            @Override
            public void onItemClick(String title) {
                switch (title) {
                    case ITEM_1:
                        ZJHiddenApiUtilsDemoActivity.launch(MainActivity.this);
                        break;
                    case ITEM_2:
                        ZJLogDemoActivity.launch(MainActivity.this);
                        break;
                    case ITEM_3:
                        ZJToastActivity.launch(MainActivity.this);
                        break;
                    case ITEM_4:
                        ZJNotificationUtilsDemoActivity.launch(MainActivity.this);
                        break;
                    default:
                        break;
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public interface OnAdapterListener {
        void onItemClick(String title);
    }

    private static class MainAdapter extends RecyclerView.Adapter {

        private final List<String> data;
        private OnAdapterListener listener;

        MainAdapter(List<String> data) {
            this.data = data;
        }

        public void setListener(OnAdapterListener listener) {
            this.listener = listener;
        }

        private View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    final String title = (String) v.getTag();
                    listener.onItemClick(title);
                }
            }
        };

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_adapter_item, parent, false);
            return new MyViewHodler(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MyViewHodler h = (MyViewHodler) holder;
            String title = data.get(position);
            h.title.setText(title);
            h.itemView.setTag(title);
            h.itemView.setOnClickListener(clickListener);
        }

        @Override
        public int getItemCount() {
            return this.data == null ? 0 : data.size();
        }

        private class MyViewHodler extends RecyclerView.ViewHolder {

            TextView title;

            public MyViewHodler(View inflate) {
                super(inflate);
                itemView.setBackground(itemView.getResources().getDrawable(R.drawable.list_item_bg));
                title = inflate.findViewById(R.id.tv_value);
            }
        }
    }
}
