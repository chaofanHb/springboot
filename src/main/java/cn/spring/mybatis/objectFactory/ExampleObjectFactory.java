//package cn.spring.mybatis.objectFactory;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Properties;
//
//import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
//
//import cn.hb.genneral.entity.User;
//
//public class ExampleObjectFactory extends DefaultObjectFactory {
//	private static final long serialVersionUID = 1L;
//	
//	private String name;
//	
//	@Override
//	public <T> T create(Class<T> type) {
//		// TODO Auto-generated method stub
//		return super.create(type);
//	}
//	
//	@Override
//	public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
//		// TODO Auto-generated method stub
//		T temp = super.create(type, constructorArgTypes, constructorArgs);
//		if(User.class.isAssignableFrom(type)) {
//			User user = (User)temp;
//			user.setTitle(this.name);
//			user.setTotalPop(0l);
//		}
//
//		System.out.println(type.getName());
//		return temp;
//	}
//	
//	@Override
//	public void setProperties(Properties properties) {
//		// TODO Auto-generated method stub
//		this.name = properties.get("name").toString();
//		super.setProperties(properties);
//	}
//	
//	@Override
//	public <T> boolean isCollection(Class<T> type) {
//		// TODO Auto-generated method stub
//		return Collection.class.isAssignableFrom(type);
//	}
//}
