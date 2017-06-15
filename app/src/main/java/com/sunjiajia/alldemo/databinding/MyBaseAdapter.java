package com.sunjiajia.alldemo.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by mk on 2017/2/7.
 * Context context：上下文，不比多说
 * List list：传进来的数据集合，不解释
 * int layoutId： item布局的资源id
 * int variableId：系统自动生成的
 */

public class MyBaseAdapter<T> extends BaseAdapter {

    private List<T> list;
    private int layoutId;
    private int variableId;
    private LayoutInflater mInflater;

    public MyBaseAdapter(List<T> list, int layoutId, int variableId, LayoutInflater mInflater) {
        this.list = list;
        this.layoutId = layoutId;
        this.variableId = variableId;
        this.mInflater = mInflater;
    }

    @Override
    public int getCount() {
        return list.size() == 0 ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding dataBinding;
        if (convertView == null) {
            dataBinding = DataBindingUtil.inflate(mInflater, layoutId, parent, false);
        } else {
            dataBinding = DataBindingUtil.getBinding(convertView);
        }
        dataBinding.setVariable(variableId, list.get(position));
        return dataBinding.getRoot();
    }
}
