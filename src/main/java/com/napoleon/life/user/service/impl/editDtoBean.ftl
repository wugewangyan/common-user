package ${template.editDtoPackageName};  
   
 <#list template.editDtoImports as being>  
 import ${being};  
 </#list>  
 
 public class ${template.editDtoClassName} extends BaseDto{  
   
    @Validator(desc = "主键ID", nullable = true, isLong = true)
    private String entityId;
   
 <#list template.editEntrys as field> 
 	/**
	 *  ${field.remark}
	 */
	@Validator(desc = "${field.remark}", nullable = true)
    private ${field.type} ${field.value};  
 </#list>
 
 	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
 
 <#list template.editEntrys as field> 
    public ${field.type} get${field.value?cap_first}() {  
        return ${field.value};  
    }  
      
    public void set${field.value?cap_first}(${field.type} ${field.value}) {  
        this.${field.value} = ${field.value};  
    }  
 </#list>
 }  