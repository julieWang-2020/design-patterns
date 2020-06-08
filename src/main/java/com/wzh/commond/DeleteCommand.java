package com.wzh.commond;

/**
 * @author wzh
 * @date 2020-06-08 22:29
 */
public class DeleteCommand extends Command {

    Content c;

    String deleted;

    public DeleteCommand(Content c) {
        this.c = c;
    }

    @Override
    public void doit() {
        deleted=c.mag.substring(0,5);
        c.mag=c.mag.substring(deleted.length(),c.mag.length());
    }

    @Override
    public void undo() {
        c.mag=deleted+c.mag;
    }
}
