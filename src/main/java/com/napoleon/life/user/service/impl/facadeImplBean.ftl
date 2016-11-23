package ${template.facadeImplPackageName};  
   
 <#list template.imports as being>  
 import ${being};  
 </#list>  
 
 @Service
 public class ${template.facadeImplName} implements ${template.facadeName}{

	@Autowired
	private ${template.serviceName} ${template.serviceNameLowerCase};
	
	@Autowired
	private CommonSerialNoService serialNoService;

	@Override
	public String edit${template.javaBeanName}(${template.editDtoBeanName} editDto) {
		${template.javaBeanName} ${template.javaBeanNameToLower} = null;
		if(StringUtil.notEmpty(editDto.getEntityId())){
			${template.javaBeanNameToLower} = ${template.serviceNameLowerCase}.findByEntityId(Long.valueOf(editDto.getEntityId()));
			if(${template.javaBeanNameToLower} == null){
				return CommonRltUtil.createCommonRltToString(CommonResultCode.NOT_FOUND);
			}
		}else{
			${template.javaBeanNameToLower} = new ${template.javaBeanName}();
			${template.javaBeanNameToLower}.setCreateTime(new Timestamp(new Date().getTime()));
		}

		${template.javaBeanNameToLower}.setDescription(editDto.getDescription());
		${template.javaBeanNameToLower}.setUpdateTime(new Timestamp(new Date().getTime()));
		
		this.${template.serviceNameLowerCase}.insertOrUpdate(${template.javaBeanNameToLower});
		return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS);
	}
	
	@Override
	public String delete${template.javaBeanName}(LifeDeleteDto deleteInfo) {
		${template.javaBeanName} ${template.javaBeanNameToLower} = this.${template.serviceNameLowerCase}.findBy${template.entityNoToQueryUpcase}(deleteInfo.getEntityId());
		if(${template.javaBeanNameToLower} == null){
			return CommonRltUtil.createCommonRltToString(CommonResultCode.NOT_FOUND);
		}
		
		this.${template.serviceNameLowerCase}.delete(${template.javaBeanNameToLower}.getId());
		return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS);
	}
}
 
