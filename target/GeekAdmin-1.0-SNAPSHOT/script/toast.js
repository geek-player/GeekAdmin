const notifications = document.querySelector('.notifications'),
    buttons = document.querySelectorAll('.buttons button'),
    toastDetails = {
        timer: 5000,
        success: {
            icon: 'images/success.svg',
            text: 'Success: This is a success toast.'
        },
        error: {
            icon: 'images/error.svg',
            text: 'Error: This is a error toast.'
        },
        warning: {
            icon: 'images/warning.svg',
            text: 'Warning: This is a warning toast.'
        },
        info: {
            icon: 'images/info.svg',
            text: 'Info: This is a info toast.'
        }
    },
    removeToast = (toast) => {
        toast.classList.add('hide')
        if (toast.timeoutId) clearTimeout(toast.timeoutId) // 清除setTimeout
        // 移除li元素
        setTimeout(() => {
            toast.remove()
        }, 500)

    }

const createToast = (id, context, path) => {
    const {
        icon,
        text
    } = toastDetails[id]
    const toast = document.createElement('li') // 创建li元素
    toast.className = `toast ${id} geek-card suspension` // 为li元素新增样式
    toast.innerHTML = `
  <div class="column">
      <img class="geek-icon" src="${path == null ? icon : path + icon}"></img>
      <span>${context == null ? text : context}</span>
   </div>
   <img class="geek-icon" src="${path == null ? "" : path }images/close.svg" onClick="removeToast(this.parentElement)"></img>`
    notifications.appendChild(toast) // 添加元素到 notifications ul
    // 5秒后 隐藏toast
    toast.timeoutId = setTimeout(() => removeToast(toast), toastDetails.timer)
}

buttons.forEach(btn => {
    btn.addEventListener('click', () => createToast(btn.id))
})