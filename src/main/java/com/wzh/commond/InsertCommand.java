package com.wzh.commond;

/**
 * @author wzh
 * @date 2020-06-08 22:29
 */
public class InsertCommand extends Command {

    Content c;

    String strToInsert="http://www.mashibing.com";

    public InsertCommand(Content c) {
        this.c = c;
    }

    @Override
    public void doit() {
        c.mag=c.mag+strToInsert;
    }

    @Override
    public void undo() {
        c.mag=c.mag.substring(0,c.mag.length()-strToInsert.length());
    }
}
