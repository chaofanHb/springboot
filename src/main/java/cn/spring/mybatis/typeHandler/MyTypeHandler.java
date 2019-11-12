//package cn.spring.mybatis.typeHandler;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.MappedJdbcTypes;
//import org.apache.ibatis.type.MappedTypes;
//import org.apache.ibatis.type.StringTypeHandler;
//
//import cn.hb.genneral.entity.Companys;
//
///**
// * 类型处理器
// * MappedTypes 全局处理java类型
// * MappedJdbcTypes 全局处理jdbc类型
// * @author chaofan
// *
// */
////@MappedTypes(String.class)
////@MappedJdbcTypes(JdbcType.VARCHAR)
//public class MyTypeHandler extends BaseTypeHandler<String> {
//
//	@Override
//	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
//			throws SQLException {
//		ps.setString(i, parameter.substring(0, 1));
//		
//	}
//
//	@Override
//	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
//		// TODO Auto-generated method stub
//		return rs.getString(columnName).substring(0, 1);
//	}
//
//	@Override
//	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//		// TODO Auto-generated method stub
//		return rs.getString(columnIndex).substring(0, 1);
//	}
//
//	@Override
//	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//		// TODO Auto-generated method stub
//		return cs.getString(columnIndex);
//	}
//
//}
