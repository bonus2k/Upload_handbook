<#assign
know = Session.SPRING_SECURITY_CONTEXT??
>

<#if know>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
<#--    balance = user.getBilling().getBalance()-->
<#--    isAdmin = user.getAuthorities()?seq_contains('ADMIN')-->
    isUser = user.getAuthorities()??
    >
<#else>
    <#assign
    name = "Гость"
    isUser = false
    >
</#if>

