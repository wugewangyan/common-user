package ${template.serviceImplPackageName};  
   
 <#list template.imports as being>  
 import ${being};  
 </#list>  
 
 @Service
 public class ${template.serviceImplName} implements ${template.serviceName}{

	@Autowired
	private ${template.daoBeanName} ${template.daoBeanNameLowerCase};

	@Transactional
	@Override
	public void insertOrUpdate(${template.javaBeanName} info) {
		if(info != null){
			if(StringUtil.notEmpty(info.getId())){
				this.${template.daoBeanNameLowerCase}.update(info);
			}else{
				this.${template.daoBeanNameLowerCase}.add(info);
			}
		}else{
			throw new CommonException(CommonResultCode.SYSTEM_ERR);
		}
	}

	@Override
	public ${template.javaBeanName} findBy${template.entityNoToQueryUpcase}(String ${template.entityNoToQuery}){
        return this.${template.daoBeanNameLowerCase}.findBy${template.entityNoToQueryUpcase}(${template.entityNoToQuery});
	}

	@Override
	public ${template.javaBeanName} findByEntityId(Long entityId) {
		return this.${template.daoBeanNameLowerCase}.get(entityId);
	}
	
	@Transactional
	@Override
	public void delete(Long entityId) {
		this.${template.daoBeanNameLowerCase}.delete(entityId);
	}
}
 
