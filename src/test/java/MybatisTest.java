import com.lagou.mapper.IOrderMapper;
import com.lagou.mapper.IUserMapper;
import com.lagou.pojo.Orders;
import com.lagou.pojo.SysRole;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author lijian
 * @create 2021-03-10 22:32
 */
public class MybatisTest {
    @Test
    public void TestQuery(){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("com.lagou.dao.IUserDao.findAll", new User());
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void TestInsert(){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(55);
        user.setUsername("王五");
        user.setPassword("3212113");
        user.setBirthday("2021-01-01");
        sqlSession.insert("user.insertUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void TestUpdate(){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(55);
        user.setUsername("张飞");
        user.setPassword("3212113");
        user.setBirthday("2021-01-01");
        sqlSession.update("com.lagou.dao.IUserDao.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void TestDelete(){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(55);
        user.setUsername("张飞");
        user.setPassword("3212113");
        user.setBirthday("2021-01-01");
        sqlSession.delete("com.lagou.dao.IUserDao.deleteUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void TestProxy(){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("-------------代理模式测试--------------");
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
        System.out.println("-------------foreach测试--------------");
        int[] array = {1,2};
        List<User> users1 = mapper.findByIds(array);
        for (User user2 : users1) {
            System.out.println(user2.toString());
        }
        System.out.println("-----------1对1----------------");
        //1对1
        IOrderMapper mapper2 = sqlSession.getMapper(IOrderMapper.class);
        List<Orders> orders = mapper2.selectOrderAndUser();
        for (Orders order : orders) {
            System.out.println(order);
        }
        System.out.println("-----------1对多----------------");
        //1对多
        IUserMapper mapper1 = sqlSession.getMapper(IUserMapper.class);
        List<User> allUserAndOrder = mapper1.findAllUserAndOrder();
        for (User user : allUserAndOrder) {
            System.out.println(user);
        }
        System.out.println("-----------多对多----------------");
        List<User> users2 = mapper1.findAllUserAndRole();
        for (User user2 : users2) {
            System.out.println(user2);
        }
    }

    //注解开发
    IUserMapper userMapper = null;
    IOrderMapper orderMapper = null;
    @Before
    public void before(){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(IUserMapper.class);
        orderMapper = sqlSession.getMapper(IOrderMapper.class);
    }
    @Test
    public void comment(){
        System.out.println("-----------1对1----------------");
        List<Orders> orders = orderMapper.selectOrderAndUser();
        for (Orders order : orders) {
            System.out.println(order);
        }
        System.out.println("-----------1对多----------------");
        List<User> users = userMapper.findAllUserAndOrder();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("-----------多对多----------------");
        List<User> users2 = userMapper.findAllUserAndRole();
        for (User user2 : users2) {
            System.out.println(user2);
        }
    }
}
