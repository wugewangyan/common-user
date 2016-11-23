package ${template.facadePackageName};  
   
 <#list template.imports as being>  
 import ${being};  
 </#list>  
 
 public interface ${template.facadeName}{

	public String edit${template.javaBeanName}(${template.editDtoBeanName} editDto);
	
	public String delete${template.javaBeanName}(LifeDeleteDto deleteInfo);
}
 
