package com.wzh.commond;

/**
 * @author wzh
 * @date 2020-06-08 22:29
 */
public class CopyCommand extends Command {

    Content c;

    public CopyCommand(Content c) {
        this.c = c;
    }

    @Override
    public void doit() {
        c.mag=c.mag+c.mag;
    }

    @Override
    public void undo() {
        c.mag=c.mag.substring(0,c.mag.length()/2);
    }
}
