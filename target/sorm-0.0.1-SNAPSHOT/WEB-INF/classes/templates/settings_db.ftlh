<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>

    <form method="post">
        <div class="row-11 border border-secondary rounded g-2">

            <div class="p-2 input-group input-group-sm col-md-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-sm">Тип БД: </span>
                </div>
                <select class="form-control" name=billing.jdbc.driverClassName>
                    <option selected>${driverApp}</option>
                    <#list drivers as driver>
                    <option value=${driver}>${driver}</option>
                    </#list>
                </select>
            </div>




            <div class="p-2 input-group input-group-sm col-md-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-sm">URL БД: </span>
                </div>
                <input type="text" id="" class="form-control" placeholder="URL БД"
                       aria-label="URL БД" name=billing.jdbc.url value="${billingURL}">
            </div>

            <div class="p-2 input-group input-group-sm col-md-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-sm">Логин:  </span>
                </div>
                <input type="text" id="" class="form-control" placeholder="Логин"
                       aria-label="Логин" name=billing.jdbc.username value="${settingsAppDB.username}">
            </div>




            <div class="p-2 input-group input-group-sm col-md-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-sm">Пароль: </span>
                </div>
                <input type="password" id="" class="form-control" placeholder="Пароль"
                       aria-label="Пароль" name=billing.jdbc.password value="${settingsAppDB.password}">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="billing.jdbc.driverClassName" value="${driver!}"/>
            <input type="hidden" name="billing.jdbc.url" value="${url!}"/>
            <input type="hidden" name="billing.jdbc.username" value="${login!}"/>
            <input type="hidden" name="billing.jdbc.password" value="${password!}"/>


            <div class="input-group-sm col-md-9">
            </div>

            <div class="p-2 input-group-sm col-md-3">
                <button class="form-control">Сохранить</button>
            </div>


        </div>

    </form>

</@c.page>