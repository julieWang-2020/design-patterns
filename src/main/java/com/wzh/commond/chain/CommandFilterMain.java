package com.wzh.commond.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020-06-09 1:20
 */
public class CommandFilterMain {
    public static void main(String[] args) {
        CommandFilterChain chain=new CommandFilterChain()
                .add(new InsertCommandFilter())
                .add(new CopyCommandFilter())
                .add(new DeleteCammandFiler());

        Content c=new Content("init context!");
        chain.doFilter(c,chain);
        System.out.println(c.msg);
    }
}


class Content {
    public Content(String msg) {
        this.msg = msg;
    }

    String msg;

}

class CommandFilterChain implements CommandFilter{

    private List<CommandFilter> commands=new ArrayList<>();

    private int index;

    public CommandFilterChain add(CommandFilter c){
        commands.add(c);
        return this;
    }


    @Override
    public void doFilter(Content c, CommandFilterChain chain) {
        if(index==commands.size()) return;
        CommandFilter filter=commands.get(index);
        index++;
        filter.doFilter(c,chain);
    }


    @Override
    public void doit(Content c) {

    }

    @Override
    public void undo(Content c) {

    }
}

interface CommandFilter {

    public void doFilter(Content c, CommandFilterChain chain);

    public void doit(Content c);

    public void undo(Content c);

}

class CopyCommandFilter implements CommandFilter{


    @Override
    public void doFilter(Content c, CommandFilterChain chain) {
        doit(c);
        chain.doFilter(c,chain);
        undo(c);
    }

    @Override
    public void doit(Content c) {
        c.msg=c.msg+c.msg;
    }

    @Override
    public void undo(Content c) {
        c.msg=c.msg.substring(0,c.msg.length()/2);
    }
}

class InsertCommandFilter implements CommandFilter{

    String strToInsert="http://www.mashibing.com";

    @Override
    public void doFilter(Content c, CommandFilterChain chain) {
        doit(c);
        chain.doFilter(c,chain);
        undo(c);
    }

    @Override
    public void doit(Content c) {
        c.msg=c.msg+strToInsert;
    }

    @Override
    public void undo(Content c) {
        c.msg=c.msg.substring(0,c.msg.length()-strToInsert.length());
    }
}

class DeleteCammandFiler implements CommandFilter{


    String deleted;

    @Override
    public void doFilter(Content c, CommandFilterChain chain) {
        doit(c);
        chain.doFilter(c,chain);
        undo(c);
    }

    @Override
    public void doit(Content c) {
        deleted=c.msg.substring(0,5);
        c.msg=c.msg.substring(deleted.length(),c.msg.length());
    }

    @Override
    public void undo(Content c) {
        c.msg=deleted+c.msg;
    }
}
