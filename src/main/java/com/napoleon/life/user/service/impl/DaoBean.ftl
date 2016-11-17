package ${template.packageName};  
   
 <#list template.imports as being>  
 import ${being};  
 </#list>  
 
import com.napoleon.life.common.persistence.GenericDao;
 
 
 public interface ${template.javaBeanName}Dao extends GenericDao<${template.javaBeanName}>{
 
	public ${template.javaBeanName} findBy${template.entityNoToQueryUpcase}(String ${template.entityNoToQuery});
}
 
