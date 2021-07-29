<#include "security.ftlh">

<nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
    <div class="container">
        <ul class="navbar-nav d-flex flex-row">

                <a class="navbar-brand me-2" href="/">
                    <img
                    /> <i class="fal fa-user-secret fa-2x"></i></a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">



                <li class="nav-item">
                    <a class="nav-link" href="/"><i class="far fa-home-lg-alt"></i> Main</a>
                </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/settings"><i class="far fa-cogs"></i> Setting</a>
                    </li>

<#--                        <li class="nav-item">-->
<#--                            <a class="nav-link" href="/orderFilter"><i class="far fa-folder-tree"></i> Setting FTP</a>-->
<#--                        </li>-->

                    <li class="nav-item">
                        <a class="nav-link" href="/user/trade"><i class="far fa-file-alt"></i> Log</a>
                    </li>

        </ul>


        <div class="d-flex align-items-center">

            <button
                    class="navbar-toggler"
                    type="button"
                    data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false"
                    aria-label="Toggle navigation">
                <i class="fas fa-bars"></i>
            </button>


<#--                    <a class="nav-link" href="/telegram/${user.id}" target="_blank"><i class="fab fa-telegram fa-2x" style="color: #0088cc"></i></a>-->
                <div class="dropdown open right-container">
                    <a
                            class="btn btn-dark-lg btn-floating"
                            href="#"
                            id="navbarDropdownMenu"
                            role="navigation"
                            data-toggle="dropdown"
                            aria-expanded="false">
                        <i class="far fa-user"></i> ${name}
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a class="dropdown-item" href="/user/setpassword">
                                <i class="far fa-key"></i> Password</a>
                        </li>

                        <li>
                            <a class="dropdown-item" href="#" onclick="postToUrl('/logout', {'_csrf':'${_csrf.token}'}, 'POST')"><i class="far fa-portal-exit"></i> Выход</a>
                        </li>
                    </ul>
                </div>

            <#--            <#if name !="Гость">-->
            <#--                <@l.logout/>-->
            <#--            </#if>-->

        </div>
    </div>
</nav>