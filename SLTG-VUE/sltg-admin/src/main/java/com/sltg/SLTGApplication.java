package com.sltg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * SLTG系统主入口
 *
 * @author styra~
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SLTGApplication {
    public static void main(String[] args) {
        SpringApplication.run(SLTGApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ~~~ SLTG-GAN 启动成功 ~~~   ლ(´ڡ`ლ)ﾞ  \n" +
                " .--.            ----------------    \n" +
                " |  |            |              |    \n" +
                " |  |            -------   ------    \n" +
                " |  |   ( ' )    _( )_ |   |         \n" +
                " |  |  (_ o _) (_ o _) |   |         \n" +
                " |  |   (_,_)    (_,_) |   |         \n" +
                " |  |                  |   |         \n" +
                " |  |———————-|         |   |         \n" +
                " .-----------|.       .—————.        ");
    }
}