package ${template.servicePackageName};  
   
 <#list template.imports as being>  
 import ${being};  
 </#list>  
 
public interface ${template.serviceName}{

	public void insertOrUpdate(${template.javaBeanName} info);
	
	
	public ${template.javaBeanName} findBy${template.entityNoToQueryUpcase}(String ${template.entityNoToQuery});


	public ${template.javaBeanName} findByEntityId(Long entityId);
	

	public void delete(Long entityId);
}
 
