package com.wzh.commond;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020-06-08 22:31
 */
public class CommandMain {

    public static void main(String[] args) {
        Content c=new Content();
        c.mag="julie";
        Command insertCommand=new InsertCommand(c);
//        insertCommand.doit();
//        insertCommand.undo();
//        System.out.println(c.mag);

        Command copyCommand=new CopyCommand(c);
//        copyCommand.doit();
//        copyCommand.undo();
//        System.out.println(c.mag);

        Command deleteCommand=new DeleteCommand(c);
//        deleteCommand.doit();
//        deleteCommand.undo();
//        System.out.println(c.mag);


        List<Command> list=new ArrayList<>();
        list.add(insertCommand);
        list.add(copyCommand);
        list.add(deleteCommand);

        for(int i=0;i<list.size();i++){
            list.get(i).doit();
        }

        System.out.println("------------------");

        System.out.println(c.mag);

        for(int i=list.size();i > 0;i--){
            list.get(i-1).undo();
        }
        System.out.println(c.mag);
    }
}
