import com.lagou.mapper.IOrderMapper;
import com.lagou.mapper.IUserMapper;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author lijian
 * @create 2021-03-13 19:19
 */
public class CacheTest {
    //注解开发
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    @Before
    public void before(){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    /*
     * @description: mybatis一级缓存测试
     * @params: []
     * @return: void
     * @author: lijian
     * @dateTime: 2021/3/13 23:24
     */
    @Test
    public void firstTest(){
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        User user1 = mapper.findUserById(1);
        sqlSession.clearCache();//清除一级缓存

        //执行修改操作
        /*User user = new User();
        user.setId(1);
        user.setUsername("王铁柱");
        mapper.updateUserById(user);
        sqlSession.commit();//commit也会清空一级缓存 */

        User user2 = mapper.findUserById(1);
        System.out.println(user1==user2);
    }

    /*
     * @description: 测试二级缓存
     * @params: []
     * @return: void
     * @author: lijian
     * @dateTime: 2021/3/15 13:18
     */
    @Test
    public void secondTest(){
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        IUserMapper mapper1 = sqlSession1.getMapper(IUserMapper.class);
        IUserMapper mapper2 = sqlSession2.getMapper(IUserMapper.class);
        IUserMapper mapper3 = sqlSession3.getMapper(IUserMapper.class);

        User user1 = mapper1.findUserById(1);
//        sqlSession1.clearCache();//执行sqlSession1.clearCache()清空一级缓存后，二级缓存未命中：说明该方法同时清空了二级缓存
        sqlSession1.close();//关闭连接，清空一级缓存（）

        //执行修改操作
        User user = new User();
        user.setId(1);
        user.setUsername("王铁柱");
        mapper3.updateUserById(user);
        sqlSession3.commit();//commit也会清空二级缓存

        User user2 = mapper2.findUserById(1);
        System.out.println(user1==user2);//二级缓存：缓存的是内容不是对象
    }
}
