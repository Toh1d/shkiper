document.addEventListener('DOMContentLoaded', () => {

    function slider(lineSlider, slideImg, widthSlide, arrowNext, arrowPrev, wrapperDots, current, currentDots, currentDotsCreate) {

        const sliderLine = document.querySelector(lineSlider)
        const slide = document.querySelectorAll(slideImg)
        const width = document.querySelector(widthSlide)
        const btnNext = document.querySelector(arrowNext)
        const btnPrev = document.querySelector(arrowPrev)
        const dotsWrapper = document.querySelector(wrapperDots)

        const dots = []

        let index = 0
        let canslide = true
        let dotIndex = 0
        let offset = width

        function init() {
            offset = width.offsetWidth
        }
        function offsetInit() {
            index = 0
            sliderLine.style.transform = `translateX(-${index * offset}px)`
            dotIndex = 0
            dotActive()
        }
        init()
        window.addEventListener('resize', () => {
            offsetInit()
            init()
        })


        function interval() {
            const interval = setInterval(() => {
                canslide = true
            }, 200);

            setInterval(() => {
                clearInterval(interval)
            }, 200);
        }



        function dotActive() {
            dots.forEach(items => {
                items.classList.remove('dot--active')
            })
            dots[dotIndex].classList.add('dot--active')
        }

        for (let i = 0; i < slide.length - currentDotsCreate; i++) {
            const createDot = document.createElement('div')

            createDot.setAttribute('data-slide', i + 1)
            createDot.classList.add('dot')

            if (i == 0) {
                createDot.classList.add('dot--active')
            }

            dotsWrapper.append(createDot)
            dots.push(createDot)
        }

        dots.forEach(item => {
            item.addEventListener('click', (e) => {
                const slideTo = e.target.getAttribute('data-slide')
                sliderLine.style.transition = `all 0.5s ease`

                index = slideTo - 1
                dotIndex = slideTo - 1
                sliderLine.style.transform = `translateX(-${index * offset}px)`

                dots.forEach(items => {
                    items.classList.remove('dot--active')
                })
                item.classList.add('dot--active')

            })
        })

        btnNext.addEventListener('click', () => {


            if (canslide == true) {

                canslide = false

                sliderLine.style.transition = `all 0.5s ease`
                if (index == slide.length - current) {
                    index = 0
                } else {
                    index++
                }

                sliderLine.style.transform = `translateX(-${index * offset}px)`

                if (dotIndex > slide.length - currentDots) {
                    dotIndex = 0
                } else {
                    dotIndex++
                    console.log(dotIndex);
                }


                interval()
                dotActive()

            }

        })

        btnPrev.addEventListener('click', () => {

            if (canslide == true) {

                canslide = false

                sliderLine.style.transition = `all 0.5s ease`
                if (index <= 0) {
                    index = slide.length - current
                } else {
                    index--
                }

                sliderLine.style.transform = `translateX(-${index * offset}px)`

                if (dotIndex < 1) {
                    dotIndex = slide.length - current
                } else {
                    dotIndex--
                }

                interval()
                dotActive()

            }



        })

    }

    slider('.slider', '.slide', '.slide', '.main-slider__next-btn', '.main-slider__prev-btn', '.dots', 1, 2, 0)
    slider('.banquet-slider__slider', '.banquet-slider__slide', '.banquet-slider__slide', '.banquet-slider__next-btn', '.banquet-slider__prev-btn', '.banquet-slider__dots', 1, 2, 0)

    const ahchors = document.querySelector('.main__button-link')

    ahchors.addEventListener('click', (e) => {
        e.preventDefault()

        document.querySelector('.page__form').scrollIntoView({
            behavior: 'smooth',
            block: 'start'
        })
    })

    const burgerBtn = document.querySelector('.burger')
    const burgerMenu = document.querySelector('.burger__menu')
    const body = document.querySelector('body')

    burgerBtn.addEventListener('click', () => {
        burgerMenu.classList.toggle('hide')
        body.classList.toggle('overflow-hide')
    })
})