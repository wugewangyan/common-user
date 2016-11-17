package ${template.packageName};  
   
 <#list template.imports as being>  
 import ${being};  
 </#list>  
 
 @Repository
 public class ${template.daoBeanName}Impl extends GenericDaoDefault<${template.javaBeanName}> implements ${template.daoBeanName}{

	@Override
	public ${template.javaBeanName} findBy${template.entityNoToQueryUpcase}(String ${template.entityNoToQuery}){
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("${template.entityNoToQuery}", ${template.entityNoToQuery});
        return (${template.javaBeanName})super.queryOne("findBy${template.entityNoToQueryUpcase}", params);
	}
}
 
