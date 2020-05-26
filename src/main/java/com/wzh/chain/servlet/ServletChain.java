package com.wzh.chain.servlet;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020-05-26 23:05
 */
public class ServletChain {

    public static void main(String[] args) {
        Request req=new Request();
        Response resp=new Response();
        FilterChain chain1=new FilterChain();
        chain1.add(new SymbolFiler());
        chain1.add(new FaceFiler());
        chain1.add(new UrlFiler());
        chain1.add(new NumberFiler());

        chain1.doFilter(req,resp);
        System.out.println(req.msg);
        System.out.println(resp.msg);
    }
}


@Data
class Request{
    String msg="";
}

@Data
class Response{
    String msg="";
}

class FilterChain {
    List<Filter> filters=new ArrayList<>();

    int index=0;

    public void add(Filter filter){
        filters.add(filter);
    }

    public void doFilter(Request req,Response resp){
        if(index == filters.size()) return;
        Filter filter=filters.get(index);
        System.out.println("index:"+index+","+filter.getClass());
        index ++;
        filter.doFilter(req,resp,this);
    }
}


interface Filter{
    void doFilter(Request req,Response resp,FilterChain chain);
}




class FaceFiler implements Filter {

    @Override
    public void doFilter(Request req,Response resp,FilterChain chain) {
        req.setMsg(req.getMsg()+" :) #");
        chain.doFilter(req,resp);
        resp.setMsg(resp.getMsg()+" ^V^ #");
    }
}
class SymbolFiler implements Filter {

    @Override
    public void doFilter(Request req,Response resp,FilterChain chain) {
        req.setMsg(req.getMsg()+" <req> #");
        chain.doFilter(req,resp);
        resp.setMsg(resp.getMsg()+" [resp] #");
    }
}
class UrlFiler implements Filter {

    @Override
    public void doFilter(Request req,Response resp,FilterChain chain) {
        req.setMsg(req.getMsg()+" mashibing.com #");
        chain.doFilter(req,resp);
        resp.setMsg(resp.getMsg()+" www.mashibing.com #");
    }
}
class NumberFiler implements Filter {

    @Override
    public void doFilter(Request req,Response resp,FilterChain chain) {
        req.setMsg(req.getMsg()+" 996 #");
        chain.doFilter(req,resp);
        resp.setMsg(resp.getMsg()+" 995 #");
    }
}

