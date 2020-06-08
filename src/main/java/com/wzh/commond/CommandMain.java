package com.wzh.commond;

/**
 * @author wzh
 * @date 2020-06-08 22:31
 */
public class CommandMain {

    public static void main(String[] args) {
        Content c=new Content();
        c.mag="julie";
        Command insertCommand=new InsertCommand(c);
        insertCommand.doit();
        insertCommand.undo();
        System.out.println(c.mag);

        Command copyCommand=new CopyCommand(c);
        copyCommand.doit();
        copyCommand.undo();
        System.out.println(c.mag);

        Command deleteCommand=new DeleteCommand(c);
        deleteCommand.doit();
        deleteCommand.undo();
        System.out.println(c.mag);

    }
}
