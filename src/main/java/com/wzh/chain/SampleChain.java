package com.wzh.chain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020-05-26 22:33
 */
public class SampleChain {

    public static void main(String[] args) {
        Msg msg=new Msg();
        msg.setMsg("大家好:)，<Script> 欢迎访问 mashibing.com，大家都是996");

        FilterChain chain1=new FilterChain();
        chain1.add(new SymbolFiler());
        chain1.add(new FaceFiler());

        FilterChain chain2=new FilterChain();
        chain2.add(new UrlFiler());
        chain2.add(new NumberFiler());
        chain1.add(chain2);

        chain1.doFilter(msg);
        System.out.println(msg);
    }

}

@Data
class Msg{
    String msg;
}


class FilterChain implements Filter {

    List<Filter> filters=new ArrayList<>();

    public void add(Filter filter){
        filters.add(filter);
    }

    public boolean doFilter(Msg msg){
        for(Filter filter:filters){
            if(!filter.doFilter(msg)) return false;
        }
        return true;
    }
}


interface Filter{
    boolean doFilter(Msg msg);
}


class FaceFiler implements Filter {

    @Override
    public boolean doFilter(Msg msg) {
        msg.setMsg(msg.getMsg().replaceAll(":\\)","^V^"));
        return false;
    }
}
class SymbolFiler implements Filter {

    @Override
    public boolean doFilter(Msg msg) {
        msg.setMsg(msg.getMsg().replaceAll("<","[").replaceAll(">","]"));
        return true;
    }
}
class UrlFiler implements Filter {

    @Override
    public boolean doFilter(Msg msg) {
        msg.setMsg(msg.getMsg().replaceAll("mashibing.com","www.mashibing.com"));
        return true;
    }
}
class NumberFiler implements Filter {

    @Override
    public boolean doFilter(Msg msg) {
        msg.setMsg(msg.getMsg().replaceAll("996","995"));
        return true;
    }
}
