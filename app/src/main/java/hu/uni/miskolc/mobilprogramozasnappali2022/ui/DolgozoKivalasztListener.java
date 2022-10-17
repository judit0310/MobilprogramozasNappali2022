package hu.uni.miskolc.mobilprogramozasnappali2022.ui;

import android.view.View;

public interface DolgozoKivalasztListener {

    void onDolgozoClick(int position, View v);

    void onDolgozoClickDelete(int position, View v);
}
